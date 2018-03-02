package yrj.khoro.CLASS;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



/**
 * Created by yrj on 24-02-2018.
 */

public class CLSS_COMENTARIO {
    private String id;
    private String user_id;
    private String nombre;
    private String content;
    private String marca;
    private int comentariosCount;
    private int like;
    private int eliminados;
    private String foto;
    private String gustado;
    private String comentarios;
    private Context contx;
    private String Anio="";
    private String mes="";
    private String dia="";
    private String seg="";
    private String mig="";
    private String nag="";

    public CLSS_COMENTARIO(Context con, String id, String user_id, String nombre, String content, String marca, int comentariosCount, int like, int eliminados, String foto, String gustado,  String comentarios){
        this.id = id;
        this.user_id = user_id;
        this.nombre = nombre;
        this.content = content;
        this.marca = marca;
        this.comentariosCount = comentariosCount;
        this.like = like;
        this.eliminados = eliminados;
        this.foto = foto;
        this.gustado = gustado;
        this.comentarios = comentarios;
        this.contx=con;
        fragmentarFecha();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public int getComentariosCount() {
        return comentariosCount;
    }
    public void setComentariosCount(int comentariosCount) {
        this.comentariosCount = comentariosCount;
    }
    public int getLike() {
        return like;
    }
    public void setLike(int like) {
        this.like = like;
    }
    public int getEliminados() {
        return eliminados;
    }
    public void setEliminados(int eliminados) {
        this.eliminados = eliminados;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getGustado() {
        return gustado;
    }
    public void setGustado(String gustado) {
        this.gustado = gustado;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public Context getContx() {
        return contx;
    }
    public void setContx(Context contx) {
        this.contx = contx;
    }

    public String getAnio() {
        return Anio;
    }

    public void setAnio(String anio) {
        Anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getSeg() {
        return seg;
    }

    public void setSeg(String seg) {
        this.seg = seg;
    }

    public String getMig() {
        return mig;
    }

    public void setMig(String mig) {
        this.mig = mig;
    }

    public String getNag() {
        return nag;
    }

    public void setNag(String nag) {
        this.nag = nag;
    }

    @Override
    public String toString() {
        return "CLSS_COMENTARIO{" +
                  "id='" + id + '\'' +
                  ", user_id='" + user_id + '\'' +
                  ", nombre='" + nombre + '\'' +
                  ", content='" + content + '\'' +
                  ", marca='" + marca + '\'' +
                  ", comentariosCount=" + comentariosCount +
                  ", like=" + like +
                  ", eliminados=" + eliminados +
                  ", foto='" + foto + '\'' +
                  ", gustado='" + gustado + '\'' +
                  ", comentarios='" + comentarios + '\'' +
                  '}';
    }

    public Cursor consultar_all(){
        CLSS_QUERY COMENTARIO= new CLSS_QUERY(contx, "comentarios", null, 1);
        SQLiteDatabase DB= COMENTARIO.getWritableDatabase();
        return COMENTARIO.select_comentarios_all(DB);
    }
    public void like(Context con, String id, int like){
        CLSS_QUERY COMENTARIO= new CLSS_QUERY(con, "comentarios", null, 1);
        SQLiteDatabase DB= COMENTARIO.getWritableDatabase();
        like++;
        COMENTARIO.update_like(DB, id, String.valueOf(like));
    }
    public void fragmentarFecha() {
       /* String[] parts = this.marca.toString().split(",");
        this.Anio= parts[1];
        this.mes= parts[2];
        this.dia= parts[3];
        this.seg= parts[4];
        this.mig= parts[5];
        this.nag= parts[6];*/

    }

}
