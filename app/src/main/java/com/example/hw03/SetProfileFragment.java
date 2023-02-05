package com.example.hw03;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hw03.databinding.FragmentSetProfileBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetProfileFragment extends Fragment
{

  FragmentSetProfileBinding binder;

    public SetProfileFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SetProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetProfileFragment newInstance()
    {
        SetProfileFragment fragment = new SetProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        binder = FragmentSetProfileBinding.inflate(inflater, container, false);
        return binder.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.set_weight_screen_title);
        binder.buttonCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mListener.cancelSetWeight();
            }
        });

        binder.buttonSetWeight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    double weight = Double.parseDouble(binder.editWeightLbs.getText().toString());
                    if (weight == 0)
                    {
                        Toast.makeText(getActivity(), "Please enter a non-zero weight.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String gender = "Female";
                    if (binder.genderRadioGroup.getCheckedRadioButtonId() == R.id.maleRadio)
                    {
                        gender = "Male";
                    }
                    mListener.setProfile(new Profile(gender, weight));
                }
                catch (NumberFormatException ex)
                {
                    Toast.makeText(getActivity(), "Please enter a valid weight.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    SetProfileListener mListener;
    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mListener = (SetProfileListener) context;
    }

    public interface SetProfileListener
    {
        void setProfile(Profile profile);
        void cancelSetWeight();
    }
}