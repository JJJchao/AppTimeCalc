package xin.wentaitu.apptimecalc;

import android.Manifest;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import xin.wentaitu.apptimecalc.util.DateStringToTimeStampUtil;

public class TimeStaticsActivity extends AppCompatActivity {

    private static final String TAG = "TimeStaticsActivity";
    private TextView name;
    private ImageView img;
    private List<OtherAppInfo> infos;
    private RecyclerView appList;

    class SortByTime implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            UsageStats u1 = (UsageStats) o1;
            UsageStats u2 = (UsageStats) o2;
            if (u1.getTotalTimeInForeground() > u2.getTotalTimeInForeground()) {
                return -1;
            }
            return 1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_statics);
        infos = new ArrayList<>();

        // 打开设置:有权查看使用情况的应用|使用情况访问权限|允许访问使用记录->找到对应APP开启权限
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
//        }

        if (ContextCompat.checkSelfPermission(TimeStaticsActivity.this, Manifest.permission.PACKAGE_USAGE_STATS) != PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }

        // 获取今天00:00的日期字符串
        Calendar calendar = Calendar.getInstance();
        String t = calendar.get(Calendar.YEAR) + "-" +
                (calendar.get(Calendar.MONTH) + 1) + "-" +
                calendar.get(Calendar.DATE) + " " + "00:00";
        // 将今天00:00日期的字符串转换为时间戳
        long time =System.currentTimeMillis() - Long.valueOf(DateStringToTimeStampUtil.getTimeStamp(t));

        // 开始时间统计
        UsageStatsManager usage = (UsageStatsManager) this.getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> list = usage.queryUsageStats(UsageStatsManager.INTERVAL_BEST, time, System.currentTimeMillis());

        // 新建另一集合,原集合直接使用for循环remove会造成删除位置不确定现象
        List<UsageStats> list2 = new ArrayList<>();
        for (int i = 0 ; i < list.size(); i++) {
            if (list.get(i).getTotalTimeInForeground() != 0) {
                Log.d("test", list.get(i).getPackageName() + "  " );
                list2.add(list.get(i));
            }
        }
        Collections.sort(list2, new SortByTime());

        PackageManager pm = getPackageManager();
        for (int i = 0 ; i < list2.size(); i++) {
            try {
                OtherAppInfo info = new OtherAppInfo();
                info.setIcon(pm.getApplicationIcon(pm.getApplicationInfo(list2.get(i).getPackageName(), PackageManager.GET_META_DATA)));
                info.setName((String) pm.getApplicationLabel(pm.getApplicationInfo(list2.get(i).getPackageName(), PackageManager.GET_META_DATA)));
                info.setPackage_name(list2.get(i).getPackageName());
                info.setTotalTime(list2.get(i).getTotalTimeInForeground());
                infos.add(info);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        appList = findViewById(R.id.app_list);
        appList.setLayoutManager(new LinearLayoutManager(this));
        appList.setAdapter(new AppInfoAdapter(infos));
    }




}
