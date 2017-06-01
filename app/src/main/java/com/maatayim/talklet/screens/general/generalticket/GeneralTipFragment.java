package com.maatayim.talklet.screens.general.generalticket;

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

public class GeneralTipFragment extends Fragment implements GeneralTipContract.View{

    public static final String ARG_POSITION = "position";

    @BindView(R.id.question_or_assertion)
    ImageView tipType_mg;

    @BindView(R.id.tip_text)
    TextView tipText;

    private int position;
    private GeneralTipPresenter presenter;

    public static GeneralTipFragment newInstance(int position) {
        GeneralTipFragment fragment = new GeneralTipFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_general_tip, container, false);
        ButterKnife.bind(this, view);

        presenter = new GeneralTipPresenter(this);
        presenter.getData(position);
        return view;
    }


    public void setTip(GeneralTipTicket generalTipTicket){
        if(generalTipTicket.isAssertion()){
//            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon, 0, 0, 0);
            tipType_mg.setImageDrawable(getContext().getResources().getDrawable(R.drawable.goal));
        }else{
            tipType_mg.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tip));
        }

        tipText.setText(generalTipTicket.getTip());

    }
}
