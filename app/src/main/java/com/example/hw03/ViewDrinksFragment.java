package com.example.hw03;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw03.databinding.FragmentViewDrinksBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDrinksFragment extends Fragment
{
    FragmentViewDrinksBinding binder;
    public int currentIndex = 0;
    private static final String DRINKS_PARAM = "VIEW-DRINKS";

    public ArrayList<Drink> getmUserDrinks()
    {
        return mUserDrinks;
    }

    public void setmUserDrinks(ArrayList<Drink> mUserDrinks)
    {
        this.mUserDrinks = mUserDrinks;
    }

    private ArrayList<Drink> mUserDrinks = new ArrayList<>();


    public ViewDrinksFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userDrinks Parameter 1.
     * @return A new instance of fragment ViewDrinksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDrinksFragment newInstance(ArrayList<Drink> userDrinks)
    {
        ViewDrinksFragment fragment = new ViewDrinksFragment();
        Bundle args = new Bundle();
        args.putSerializable(DRINKS_PARAM, userDrinks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mUserDrinks = (ArrayList<Drink>) getArguments().getSerializable(DRINKS_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        binder = FragmentViewDrinksBinding.inflate(inflater, container, false);
        return binder.getRoot();
    }

    public interface ViewDrinksListener
    {
        void closeDrinks();
    }

    ViewDrinksListener mListener;

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mListener = (ViewDrinksListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        updateDrinksInfo();
        binder.drinkRightImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (currentIndex == mUserDrinks.size() - 1)
                {
                    currentIndex = 0;
                }
                else
                {
                    currentIndex += 1;
                }
                updateDrinksInfo();
            }
        });

        binder.drinkLeftImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                updateDrinksInfo();
            }
        });

        binder.drinkDeleteImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mUserDrinks.remove(currentIndex);
                updateDrinksInfo();
            }

        });

        binder.viewDrinksCloseBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mListener.closeDrinks();
            }
        });
    }

    public void updateDrinksInfo()
    {
        Drink currentDrink = mUserDrinks.get(currentIndex);
        binder.textDrinksOutOf.setText("Drink " + (currentIndex + 1) + " out of " + mUserDrinks.size());
        binder.textDrinkOz.setText(currentDrink.getDrinkSize() + " oz");
        binder.textAlcPct.setText(currentDrink.getAlcPercent() + "% Alcohol");
        binder.textDrinkAdded.setText(currentDrink.getAddedOn().toString());
    }
}