package xin.wentaitu.apptimecalc;

import android.graphics.drawable.Drawable;

/**
 * Created by wentai on 18-4-2.
 */

public class OtherAppInfo {

    private Drawable icon;
    private String name;
    private String package_name;
    private long totalTime;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
}
