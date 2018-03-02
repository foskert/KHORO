package yrj.khoro.CLASS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yrj on 24-02-2018.
 */

public class CLSS_QUERY extends SQLiteOpenHelper {
    String sql_tabla_comentarios="CREATE TABLE comentarios (ID TEXT,  ID_USER TEXT,  NOMBRE TEXT,  CONTENT TEXT, MARCA TEXT,  COMENTARIOCOUNT TEXT, LIKE TEXT, ELIMINADO TEXT, FOTO TEXT,  GUSTADO TEXT, COMENTARIO TEXT)";
    public CLSS_QUERY(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql_tabla_comentarios);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(sql_tabla_comentarios);
    }
    public void insert_comentarios(SQLiteDatabase sqLiteDatabase, String ID ,  String ID_USER , String  NOMBRE, String CONTENT , String MARCA, String COMENTARIOCOUNT , String LIKE , String ELIMINADO , String FOTO ,  String GUSTADO ,String  COMENTARIO ) {
        Cursor c=select_comentarios(sqLiteDatabase, ID);if(c.getCount()==0){
            sqLiteDatabase.execSQL("INSERT INTO comentarios (ID,  ID_USER,  NOMBRE,  CONTENT, MARCA,  COMENTARIOCOUNT, LIKE, ELIMINADO, FOTO,  GUSTADO, COMENTARIO) VALUES('"+ID+"', '"+ID_USER+"', '"+NOMBRE+"', '"+CONTENT+"', '"+MARCA+"', '"+COMENTARIOCOUNT+"', '"+LIKE+"', '"+ELIMINADO+"', '"+FOTO+"', '"+GUSTADO+"', '"+COMENTARIO+"' )");
        }
    }
    public Cursor select_comentarios_all(SQLiteDatabase sqLiteDatabase){
        return sqLiteDatabase.rawQuery("SELECT ID,  ID_USER,  NOMBRE,  CONTENT, MARCA,  COMENTARIOCOUNT, LIKE, ELIMINADO, FOTO,  GUSTADO, COMENTARIO FROM comentarios ", null);
    }
    public Cursor select_comentarios(SQLiteDatabase sqLiteDatabase, String Clave){
        return sqLiteDatabase.rawQuery("SELECT ID,  ID_USER,  NOMBRE,  CONTENT, MARCA,  COMENTARIOCOUNT, LIKE, ELIMINADO, FOTO,  GUSTADO, COMENTARIO FROM comentarios WHERE  ID='"+Clave+"' ", null);
    }
    public  void update_like(SQLiteDatabase sqLiteDatabase, String ID, String LIKE){
        sqLiteDatabase.execSQL("UPDATE comentarios SET LIKE='"+LIKE+"'  WHERE ID='"+ID+"' " );
     }
    public void delete_comentarios(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DELETE FROM  comentarios");
    }

}
