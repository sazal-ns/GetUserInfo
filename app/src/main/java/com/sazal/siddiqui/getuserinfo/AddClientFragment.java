/*
 * Copyright (c) 2017. By Noor Nabiul Alam Siddiqui
 */

package com.sazal.siddiqui.getuserinfo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClientFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.statusSpinner) Spinner statusSpinner;
    @BindView(R.id.distributorSpinner) Spinner distributorSpinner;
    @BindView(R.id.sectionSpinner) Spinner sectionSpinner;
    @BindView(R.id.packageSpinner) Spinner packageSpinner;
    @BindView(R.id.typeSpinner) Spinner typeSpinner;
    @BindView(R.id.roadEditText) EditText roadEditText;
    @BindView(R.id.houseEditText) EditText houseEditText;
    @BindView(R.id.levelEditText) EditText levelEditText;
    @BindView(R.id.tvEditText) EditText tvEditText;
    @BindView(R.id.cardNumberEditText) EditText cardNumberEditText;
    @BindView(R.id.nameEditText) EditText nameEditText;
    @BindView(R.id.customerNumberEditText) EditText customerNumberEditText;
    @BindView(R.id.mobileEditText) EditText mobileEditText;
    @BindView(R.id.emailEditText) EditText emailEditText;
    @BindView(R.id.altConNumberEditText) EditText altConNumberEditText;
    @BindView(R.id.firstConDateEditText) EditText firstConDateEditText;
    @BindView(R.id.addClientButton) AppCompatButton addClientButton;

    private String road, house, level, email, mobile, cardNumber, name,customerNumber, altConNumber, firstConDate;
    private int status, distributor, section, packages, type,tv;

    private Calendar calendar= Calendar.getInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClientFragment newInstance(String param1, String param2) {
        AddClientFragment fragment = new AddClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_cient, container, false);

        ButterKnife.bind(this, view);

        firstConDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        return view;
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                firstConDateEditText.setText(formatDate(calendar.getTime()));
                //toDate = formatDate(calendar.getTime());
        }
    };

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String hireDate = sdf.format(date);
        return hireDate;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.addClientButton)
    public void onClick(View view) {

        road = roadEditText.getText().toString();
        house = houseEditText.getText().toString();
        level = levelEditText.getText().toString();
        email = emailEditText.getText().toString();
        mobile = mobileEditText.getText().toString().trim();
        cardNumber = cardNumberEditText.getText().toString().trim();
        name = nameEditText.getText().toString().trim();
        customerNumber = customerNumberEditText.getText().toString().trim();
        altConNumber = altConNumberEditText.getText().toString().trim();
        firstConDate = firstConDateEditText.getText().toString().trim();
        //tv = Integer.valueOf(tvEditText.getText().toString());

        addClient();
    }

    private void addClient() {
        if (!validate()) {
            onValidateFailed();
            return;
        }
    }

    private void onValidateFailed() {

    }

    private boolean validate() {
        boolean valid = true;

        if (road.isEmpty()){
            roadEditText.setError("Give Input");
            valid = false;
        }else roadEditText.setError(null);

        if (house.isEmpty()){
            houseEditText.setError("Give Input");
            valid = false;
        }else houseEditText.setError(null);

        if (level.isEmpty()){
            levelEditText.setError("Give Input");
            valid = false;
        }else levelEditText.setError(null);

        if (tvEditText.getText().toString().isEmpty()){
            tvEditText.setError("Give Input");
            valid = false;
        }else tvEditText.setError(null);

        if (name.isEmpty()){
            nameEditText.setError("Give Input");
            valid = false;
        }else nameEditText.setError(null);

        if (customerNumber.isEmpty()){
            customerNumberEditText.setError("Give Input");
            valid = false;
        }else customerNumberEditText.setError(null);

        if (mobileEditText.getText().toString().isEmpty()){
            mobileEditText.setError("Give Input");
            valid = false;
        }else mobileEditText.setError(null);

        if (firstConDate.isEmpty()){
            firstConDateEditText.setError("Give Input");
            valid = false;
        }else firstConDateEditText.setError(null);



        return valid;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
