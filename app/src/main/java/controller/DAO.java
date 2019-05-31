package controller;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DAO {
    private MongoClient mongoClient;
    private DBCollection collection;
    private DB database;

    public void conectar(){
        try{
            mongoClient = new MongoClient("127.0.0.1", 27017);
            database = mongoClient.getDB("prueba");
            collection = database.getCollection("libros");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void desconectar(){
        try{
            mongoClient.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Libro consultar(String id){

        Libro libro = null;
        try{
            conectar();
            BasicDBObject query = new BasicDBObject();
            query.put("_id", id);

            DBObject cursor = collection.findOne(query);

            System.out.println(cursor.get("_id"));

            if(cursor.get("_id")!=null) {
                libro = new Libro(cursor.get("_id").toString(),
                        cursor.get("nombre").toString(),
                        Float.parseFloat(cursor.get("costo").toString()),
                        Integer.parseInt(cursor.get("numInventario").toString()),
                        cursor.get("descripcion").toString());
            }else{
                libro = new Libro();
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{desconectar();}
        return libro;
    }

    public int insertar(String nombre, String costo, String descripcion, String inventario){
        int nextId=0;
        try{
            conectar();


            nextId = Integer.parseInt(collection.find().sort(new BasicDBObject("_id ", -1))
                    .limit(1).toArray().get(0).get("_id").toString()) + 1;

            BasicDBObject documentDetail = new BasicDBObject();
            documentDetail.put("_id", nextId+"");
            documentDetail.put("nombre", nombre);
            documentDetail.put("costo", costo);
            documentDetail.put("descripcion", descripcion);
            documentDetail.put("numInventario", inventario);

            collection.insert(documentDetail).getN();

        }catch(Exception e){
            e.printStackTrace();
        }
        finally{desconectar();}
        return nextId;
    }


    public int modificar(String id, String nombre, String costo, String descripcion, String inventario){
        int n=0;
        try{
            conectar();
            BasicDBObject documentDetail = new BasicDBObject();
            documentDetail.put("_id", id);
            documentDetail.put("nombre", nombre);
            documentDetail.put("costo", costo);
            documentDetail.put("descripcion", descripcion);
            documentDetail.put("numInventario", inventario);

            BasicDBObject query = new BasicDBObject();
            query.put("_id", id);

            n = collection.update(query, documentDetail).getN();

        }catch(Exception e){
            e.printStackTrace();
        }
        finally{desconectar();}
        return n;
    }

}
