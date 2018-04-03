package xin.wentaitu.apptimecalc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wentai on 18-4-2.
 */

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder> {

    private List<OtherAppInfo> infos;

    public AppInfoAdapter(List<OtherAppInfo> infos) {
        this.infos = infos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_info_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OtherAppInfo info = infos.get(position);
        holder.app_icon.setImageDrawable(info.getIcon());
        holder.app_time.setText(String.valueOf(info.getTotalTime() / 1000) + "秒");
        holder.app_name.setText(info.getName());
        holder.app_package_name.setText(info.getPackage_name());
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView app_icon;
        TextView app_time;
        TextView app_name;
        TextView app_package_name;

        public ViewHolder(View itemView) {
            super(itemView);
            app_icon = itemView.findViewById(R.id.app_icon);
            app_time = itemView.findViewById(R.id.app_time);
            app_name = itemView.findViewById(R.id.app_name);
            app_package_name = itemView.findViewById(R.id.app_package_name);
        }
    }

}
