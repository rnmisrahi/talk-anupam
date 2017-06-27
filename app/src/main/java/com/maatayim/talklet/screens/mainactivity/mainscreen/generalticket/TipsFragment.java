package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maatayim.talklet.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sophie on 5/26/2017.
 */

public class TipsFragment extends Fragment implements TipsContract.View{

    public static final String TICKET = "ticket";
    private TipTicket ticket;


    @BindView(R.id.question_or_assertion)
    ImageView tipType_mg;

    @BindView(R.id.tip_text)
    TextView tipText;

    public static TipsFragment newInstance(TipTicket ticket) {
        TipsFragment fragment = new TipsFragment();
        Bundle args = new Bundle();
        args.putParcelable(TICKET, ticket);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ticket = getArguments().getParcelable(TICKET);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_general_tip, container, false);
        ButterKnife.bind(this, view);
        setTip(ticket);
        return view;
    }


    public void setTip(TipTicket generalTipTicket){
        tipText.setText(generalTipTicket.getTip());

        if(generalTipTicket.isAssertion()){
            tipType_mg.setImageDrawable(getContext().getResources().getDrawable(R.drawable.goal));
        }else{
            tipType_mg.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tip));
        }



    }




}
