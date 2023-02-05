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

import com.example.hw03.databinding.FragmentBacCalculatorBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BacCalculatorFragment extends Fragment
{
    FragmentBacCalculatorBinding binder;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PROFILE = "PROFILE";

    // TODO: Rename and change types of parameters
    public Profile mUserProfile;

    public Profile getmUserProfile()
    {
        return mUserProfile;
    }

    public void setmUserProfile(Profile mUserProfile)
    {
        this.mUserProfile = mUserProfile;
        updateUserInfo();
    }

    public BacCalculatorFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BacCalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BacCalculatorFragment newInstance(Profile user)
    {
        BacCalculatorFragment fragment = new BacCalculatorFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROFILE, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mUserProfile = (Profile) getArguments().getSerializable(ARG_PROFILE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        binder = FragmentBacCalculatorBinding.inflate(inflater, container, false);
        return binder.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.bac_screen_title);
        updateUserInfo();

        binder.setWeightBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mListener.goSetWeight();
            }
        });

        binder.resetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mUserProfile = null;
                updateUserInfo();
                binder.viewDrinksBtn.setEnabled(false);
            }
        });

        binder.addDrinkBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mListener.goToDrinks();
            }
        });

        binder.viewDrinksBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mUserProfile.getUserDrinks().isEmpty())
                {
                    Toast.makeText(getActivity(), "Please add drinks to view them.", Toast.LENGTH_SHORT).show();
                    return;
                }
                mListener.goToViewDrinks(mUserProfile.getUserDrinks());
            }
        });
    }

    public void updateUserInfo()
    {
        if (mUserProfile == null)
        {
            binder.bacStatusText.setText(R.string.safe_label);
            binder.bacLevelValueText.setText(R.string.default_bac_level);
            binder.bacStatusText.setBackgroundResource(R.color.safe_color);
            binder.numDrinksCountText.setText(getString(R.string.num_drinks_label) + " 0");
            binder.weightValueText.setText(R.string.default_weight_label);
            binder.viewDrinksBtn.setEnabled(false);
            binder.addDrinkBtn.setEnabled(false);
        }
        else
        {
            binder.weightValueText.setText(mUserProfile.getWeight() + " lbs (" + mUserProfile.getGender() + ")");
            binder.numDrinksCountText.setText(getString(R.string.num_drinks_label) + " " + mUserProfile.getUserDrinks().size());
            binder.viewDrinksBtn.setEnabled(true);
            binder.addDrinkBtn.setEnabled(true);
            updateBACInfo();
        }
    }

    BacHomeListener mListener;
    public void updateBACInfo()
    {
        double userBAC = mUserProfile.calculateBAC();
        binder.bacLevelValueText.setText(String.format("%.3f", userBAC));
        binder.bacStatusText.setText(R.string.safe_label);
        binder.bacStatusText.setBackgroundResource(R.color.safe_color);
        if (userBAC > 0.08 && userBAC <= 0.2)
        {
            binder.bacStatusText.setText(R.string.careful_label);
            binder.bacStatusText.setBackgroundResource(R.color.careful_color);
        }
        else if (userBAC > 0.2)
        {
            binder.bacStatusText.setText(R.string.over_limit_label);
            binder.bacStatusText.setBackgroundResource(R.color.over_limit_color);
        }
        if (userBAC >= 0.25)
        {
            Toast.makeText(getActivity(), "No more drinks for you!", Toast.LENGTH_SHORT).show();
            binder.addDrinkBtn.setEnabled(false);
        }

    }

    public interface BacHomeListener
    {
        void goSetWeight();
        void goToDrinks();
        void goToViewDrinks(ArrayList<Drink> userDrinks);

    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mListener = (BacHomeListener) context;
    }
}