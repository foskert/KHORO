package yrj.khoro.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yrj.khoro.CLASS.CLSS_COMENTARIO;
import yrj.khoro.R;
/**
 * Created by yrj on 24-02-2018.
 */

public class ADP_COMENTARIO extends BaseAdapter {
    public  static Context context;
    LayoutInflater inflater;
    private List<CLSS_COMENTARIO> Lista = null;
    private ArrayList<CLSS_COMENTARIO> array;

    public ADP_COMENTARIO(Context context, List<CLSS_COMENTARIO> lista) {
        this.context = context;
        Lista = lista;
        inflater = LayoutInflater.from(this.context);
        this.array = new ArrayList<CLSS_COMENTARIO>();
        this.array.addAll(Lista);
    }
    public class ViewHolder{
        ImageView id_img_icono_user;
        TextView id_text_username;
        TextView id_text_tiempo_agregado;
        ImageView id_img_configuracion;
        ImageView id_img_lc_principal;
        ImageView id_img_like;
        TextView id_text_like;
        ImageView id_img_comentario;
        TextView id_text_comentario;
        ImageView id_img_tiempo;
        TextView id_text_tiempo;
        TextView id_text_comentarios;
    }
    @Override
    public int getCount() {
        return Lista.size();
    }

    @Override
    public Object getItem(int i) {
        return Lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.sty_comentario, null);
            holder.id_img_icono_user = (ImageView) view.findViewById(R.id.id_img_icono_user);
            holder.id_text_username = (TextView) view.findViewById(R.id.id_text_username);
            holder.id_text_tiempo_agregado = (TextView) view.findViewById(R.id.id_text_tiempo_agregado);
            holder.id_img_configuracion = (ImageView) view.findViewById(R.id.id_img_configuracion);
            holder.id_img_lc_principal = (ImageView) view.findViewById(R.id.id_img_lc_principal);
            holder.id_img_like = (ImageView) view.findViewById(R.id.id_img_like);
            holder.id_text_like = (TextView) view.findViewById(R.id.id_text_like);
            holder.id_img_comentario = (ImageView) view.findViewById(R.id.id_img_comentario);
            holder.id_text_comentario = (TextView) view.findViewById(R.id.id_text_comentario);
            holder.id_img_tiempo = (ImageView) view.findViewById(R.id.id_img_tiempo);
            holder.id_text_tiempo = (TextView) view.findViewById(R.id.id_text_tiempo);
            holder.id_text_comentarios= (TextView) view.findViewById(R.id.id_text_comentarios);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.id_text_username.setText(Lista.get(i).getNombre());
        //holder.id_text_tiempo_agregado.setText(Lista.get(i).getMig()+" min");
        holder.id_img_configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Config  " , Toast.LENGTH_LONG).show();
            }
        });
         holder.id_img_lc_principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "ZOOM  " , Toast.LENGTH_LONG).show();
            }
        });
        holder.id_text_comentarios.setText(Lista.get(i).getContent());
        holder.id_img_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lista.get(i).like(context, Lista.get(i).getId().toString(), Lista.get(i).getLike());
                Lista.get(i).setLike(Lista.get(i).getLike()+1);
                notifyDataSetChanged();
            }
        });
        holder.id_text_like.setText(String.valueOf(Lista.get(i).getLike()));
        holder.id_img_comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Coment  " , Toast.LENGTH_LONG).show();
            }
        });
        holder.id_text_comentario.setText(String.valueOf(Lista.get(i).getComentariosCount()));
        holder.id_img_tiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Time  " , Toast.LENGTH_LONG).show();
            }
        });
        //holder.id_text_tiempo.setText(Lista.get(i).getMig()+" min");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
