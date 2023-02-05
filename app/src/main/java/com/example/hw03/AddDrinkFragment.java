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

import com.example.hw03.databinding.FragmentAddDrinkBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddDrinkFragment extends Fragment
{
    FragmentAddDrinkBinding binder;
    public AddDrinkFragment()
    {
        // Required empty public constructor
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
        binder = FragmentAddDrinkBinding.inflate(inflater, container, false);
        return binder.getRoot();
    }

    AddDrinkListener mListener;

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mListener = (AddDrinkListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binder.buttonCancelAddDrink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mListener.cancelAddDrink();
            }
        });

        binder.buttonAddDrink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                double drinkSize = 1;
                double alcPercent = binder.alcoholPctSeekbar.getProgress();
                if (alcPercent == 0)
                {
                    Toast.makeText(getActivity(), "Please choose a non-zero alcohol percent", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (binder.radioGroup.getCheckedRadioButtonId() == R.id.radio5Oz)
                {
                    drinkSize = 5;
                }
                else if (binder.radioGroup.getCheckedRadioButtonId() == R.id.radio12Oz)
                {
                    drinkSize = 12;
                }
                mListener.addDrink(new Drink(drinkSize, alcPercent));
            }
        });
    }

    public interface AddDrinkListener
    {
        void addDrink(Drink drink);
        void cancelAddDrink();
    }
}