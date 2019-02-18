package com.example.contentprovider;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class OneFragment extends Fragment {
 ArrayList<ContactsList> arrayList=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         Bundle bundle=getArguments();
        final ArrayList<ContactsList> contactsList= (ArrayList<ContactsList>) bundle.getSerializable("array");
        View view= inflater.inflate(R.layout.fragment_one, container, false);



    button=view.findViewById(R.id.button1);







    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SecondFragment secondFragment=new SecondFragment();
            Log.e("FragmentONe", String.valueOf(contactsList));
            Bundle args = new Bundle();
            args.putSerializable("array",arrayList);
            secondFragment.setArguments(args);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragmentfirst, secondFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();






        }
    });



    return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



}
