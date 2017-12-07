package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sophie on 5/26/2017
 */

public class TipsFragment extends Fragment implements TipsContract.View{

    public static final String TICKET = "ticket";
    public static final String IS_REC = "isRec";
    public static final String IS_MORE_THAN_CHILD = "isMoreThanOneChild";
    public static final String QUESTION = "Question";
    public static final String ASSERT = "Assert";
    private TipTicket ticket;


    @BindView(R.id.question_or_assertion)
    ImageView tipType_mg;

    @BindView(R.id.tip_text)
    TextView tipText;

    @BindView(R.id.child_img)
    CircleImageView childImg;

    private boolean isRec;
    private boolean isMoreThanOneChild;
    private View view;

    public static TipsFragment newInstance(TipTicket ticket, boolean isRecord, boolean isMoreThanOneChild) {
        TipsFragment fragment = new TipsFragment();
        Bundle args = new Bundle();
        args.putParcelable(TICKET, ticket);
        args.putBoolean(IS_REC, isRecord);
        args.putBoolean(IS_MORE_THAN_CHILD, isMoreThanOneChild);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_general_tip, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            ticket = getArguments().getParcelable(TICKET);
            isRec = getArguments().getBoolean(IS_REC);
            isMoreThanOneChild = getArguments().getBoolean(IS_MORE_THAN_CHILD);
        }
        setTip(ticket);
        return view;
    }


    public void setTip(TipTicket generalTipTicket){

//        if(generalTipTicket.isDisplay()) {
        tipText.setText(generalTipTicket.getTip());

        if (generalTipTicket.getTipType().equals(ASSERT)) {
            tipType_mg.setImageDrawable(getContext().getResources().getDrawable(R.drawable.goal));
        } else {
            tipType_mg.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tip));
        }


        if (isRec) {
            view.setBackgroundColor(getResources().getColor(R.color.darker_background_color));
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.tip_background));
        }

//        if(!isMoreThanOneChild) {
//            childImg.setVisibility(View.GONE);
//
//        }else{

        Picasso.with(getContext())
                .load(generalTipTicket.getBabyPhoto())
               .fit()
               .centerCrop()
                .placeholder(R.drawable.pic)
                .into(childImg);

        childImg.setVisibility(View.VISIBLE);
//        }


//        }




    }




}
