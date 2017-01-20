package eknow.com.eknow.service;

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
import eknow.com.eknow.user.SelectPhoneRegionListAdaptor;
import me.gujun.android.taggroup.TagGroup;

public class SelectTagDialog extends Dialog {

    Context context;

    public SelectTagDialog(Context context) {
        super(context);
        this.context = context;
    }
    public SelectTagDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.select_tag_dialog);

        final TagGroup mTagGroup = (TagGroup) findViewById(R.id.tag_group);
        mTagGroup.setTags(new String[]{"Tag1", "Tag2", "Tag3"});
        mTagGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] tags = mTagGroup.getTags();
                for (String tag : tags){
                    System.out.println(tag);
                }
            }
        });

//        TagCloudLinkView tagView = (TagCloudLinkView) view.findViewById(R.id.tag_group);
//        tagView.add(new Tag(1,"TAG TEXT 1"));
//        tagView.add(new Tag(1,"TAG TEXT 2"));
//        tagView.add(new Tag(1,"TAG TEXT 3"));
//        tagView.drawTags();

//        List<String> tags = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            tags.add("标签" + i);
//        }
//
//        TagCloudView tagCloudView1 = (TagCloudView) view.findViewById(R.id.tag_group);
//        tagCloudView1.setTags(tags);
//        tagCloudView1.setOnTagClickListener(
//                new TagCloudView.OnTagClickListener(){
//                    @Override
//                    public void onTagClick(int position) {
//                        if (position == -1) {
//                            Toast.makeText(getActivity().getApplicationContext(), "点击末尾文字",
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getActivity().getApplicationContext(), "点击 position : " + position,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//        tagCloudView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity().getApplicationContext(), "TagView onClick",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
