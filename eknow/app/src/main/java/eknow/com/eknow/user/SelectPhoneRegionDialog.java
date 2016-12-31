package eknow.com.eknow.user;

/**
 * Created by jianguog on 16/12/31.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.R;

public class SelectPhoneRegionDialog extends Dialog {

    Context context;
    private List<String> splitList;
    private  List<String> totallist;
    private TextView phoneRegionText;

    public SelectPhoneRegionDialog(Context context) {
        super(context);
        this.context = context;
    }
    public SelectPhoneRegionDialog(Context context, int theme, TextView phoneRegionText){
        super(context, theme);
        this.context = context;
        this.phoneRegionText = phoneRegionText;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.sign_select_phone_region);
        ListView list = (ListView) findViewById(R.id.selectRegion);
        setData();
        SelectPhoneRegionListAdaptor adapter = new SelectPhoneRegionListAdaptor(context, splitList, totallist);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                phoneRegionText.setText(totallist.get(arg2));
                hide();
            }
        });

//        TextView phoneRegionBack = (TextView) findViewById(R.id.signin_phone_region_back);
//        phoneRegionBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hide();
//            }
//        });

    }

    void setData(){
        splitList = new ArrayList<String>();
        splitList.add("热门");
        splitList.add("欧洲");
        splitList.add("亚洲");
        splitList.add("大洋洲");
        splitList.add("非洲");
        splitList.add("美洲");

        totallist = new ArrayList<String>();
        String[][] data = {
                {"中国大陆 +86", "美国 +1"},
                {"爱尔兰 +353", "奥地利 +43" , "比利时 +32", "波兰 +48", "冰岛 +354", "丹麦 +45", "德国 +49",
                        "俄罗斯 +7", "法国 +33", "芬兰 +358", "梵蒂冈 +379", "荷兰 +31", "捷克 +420", "列支敦士登 +423",
                        "立陶宛 +370",  "卢森堡 +352",  "罗马尼亚 +40",  "摩纳哥 +377",  "挪威 +47", "葡萄牙 +351",
                        "瑞典 +46",  "瑞士 +41",  "斯洛伐克 +421",  "摩纳哥 +377",  "挪威 +47", "土耳其 +90",
                        "乌克兰 +380",  "西班牙 +34",  "希腊 +30",  "英国 +44",  "意大利 +39" },
                {"阿联酋 +971", "不丹 +975", "菲律宾 +63", "韩国 +82", "柬埔寨 +855", "马尔代夫 +960",
                        "孟加拉国 +880", "马来西亚 +60","日本 +81", "澳门特别行政区 +853", "沙特阿拉伯 +966",
                        "泰国 +66", "台湾 +886", "文莱 +673", "香港特别行政区 +852", "新加坡 +65",
                        "'印度' +91", "'印尼' +62", "'越南' +84", "'以色列' +972"},
                {"澳大利亚 +61", "新西兰 +64"},
                {"埃及 +20", "塞舌尔 +248"},
                {"巴西 +55", "墨西哥 +52", "加拿大 +1"},
        };

        int i = 0;
        for(String region: splitList) {
            totallist.add(region);
            for(String country : data[i]){
                totallist.add(country);
            }
            i++;
        }
    }

}
