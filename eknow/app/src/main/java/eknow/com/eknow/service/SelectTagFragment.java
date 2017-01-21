package eknow.com.eknow.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;
import me.gujun.android.taggroup.TagGroup;

public class SelectTagFragment extends BaseFragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setToolbarTitle(R.string.serviceEditTags);
        view = inflater.inflate(R.layout.select_tag_fragment, container, false);
        final TagGroup mTagGroup = (TagGroup) view.findViewById(R.id.tag_group);
        String[] tags = getArguments().getStringArray("tags");
        mTagGroup.setTags(tags);
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

        return view;
    }
}