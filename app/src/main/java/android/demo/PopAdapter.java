package android.demo;

import android.popwindow.CustomPopWindow;
import android.popwindow.ViewUtil;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * Created by zhangke on 2018/6/13.
 */

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.ViewHolder>{

    public PopAdapter() {

    }
    @Override
    public PopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);
        PopAdapter.ViewHolder viewHolder = new PopAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(PopAdapter.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 20;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
          Button alertButtom;

        public ViewHolder(final View itemView) {
            super(itemView);
            alertButtom = itemView.findViewById(R.id.alert_button);
            alertButtom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.edit_pop_window,null);
                    int windowPos[] = ViewUtil.calculatePopWindowPos(alertButtom, view);
                    int xOff = ViewUtil.dip2px(20);//偏移
                    windowPos[0] -= xOff;
                    final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(itemView.getContext())
                            .setView(view)
                            .setFocusable(true)
                            .setOutsideTouchable(true)
                            .create();
                    popWindow.showAtLocation(view, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
                }
            });
        }
    }
}
