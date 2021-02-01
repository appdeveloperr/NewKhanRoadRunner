package com.example.husnain.newproject.activities.cashdetail;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.husnain.newproject.Global;
import com.example.husnain.newproject.PrintPakage.P25ConnectionException;
import com.example.husnain.newproject.R;
import com.example.husnain.newproject.Utils.PrinterCommands;
import com.example.husnain.newproject.Utils.Utile;
import com.example.husnain.newproject.activities.MainActivity;
import com.example.husnain.newproject.entities.TicketingSeat;
import com.example.husnain.newproject.viewmodels.CityViewModel;
import com.example.husnain.newproject.viewmodels.TicketingSeatViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CashDetailFragment extends Fragment {

    OnListFragmentInteractionListener mListener;
    private TicketingSeatViewModel ticketingSeatViewModel;
    private CityViewModel cityViewModel;
    private List<CashDetail> cashDetails = new ArrayList<>(0);
    private RecyclerView recyclerView;
    private MyCashDetailRecyclerViewAdapter myCashDetailRecyclerViewAdapter;

    public static CashDetailFragment newInstance() {
        return new CashDetailFragment();
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CashDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketingSeatViewModel = ViewModelProviders.of(this).get(TicketingSeatViewModel.class);
        cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        myCashDetailRecyclerViewAdapter = new MyCashDetailRecyclerViewAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cashdetail_list, container, false);


        // Set the adapter
        this.recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setAdapter(this.myCashDetailRecyclerViewAdapter);
        this.reload();
        View actionPrint = view.findViewById(R.id.action_print);
        actionPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                print();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        this.reload();
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void reload() {
        if (this.recyclerView == null) {
            Toast.makeText(this.getContext(), "Recycler View is null.", Toast.LENGTH_LONG);
            System.out.println("Recycler View is null.");
            return;
        }
//        List<TicketingSeat> myTickets = new ArrayList<>(0);
//        for (int i = 1; i <= 10; i++) {
//            myTickets.add(new TicketingSeat(i, "Customer: " + i, new Random().nextDouble() * 1000, 1, 1));
//        }
        List<TicketingSeat> myTickets = this.ticketingSeatViewModel.getMyTickets(Global.User_ID);
        cashDetails.clear();
        cashDetails.add(null);
        double fare = 0;
        double discount = 0;
        double total = 0;
        for (TicketingSeat myTicket : myTickets) {
            CashDetail detail = new CashDetail(myTicket);
            detail.setSource(this.cityViewModel.getCityById(myTicket.getSource_ID()));
            detail.setDestination(this.cityViewModel.getCityById(myTicket.getDestination_ID()));
            cashDetails.add(detail);

            fare += detail.getFare();
            discount += detail.getDiscount();
            total += detail.getAmount();
        }
        CashDetail cd = new CashDetail(new TicketingSeat(0, " ", total, 0, 0));
        cd.setSourceAbbr("");
        cd.setFare(fare);
        cd.setDiscount(discount);
        cd.setAmount(total);
        cd.setSeatNo(cashDetails.size() - 1);
        cashDetails.add(null);
        cashDetails.add(cd);
        this.myCashDetailRecyclerViewAdapter.setmValues(this.cashDetails);

    }

    private void print() {
        if (MainActivity.mConnector == null || !MainActivity.mConnector.isConnected()) {
            Utile.showAlertDialog("Please attach printer first.", this.getContext()).show();
            return;
        }

        List<String> list = new ArrayList<>(0);

        list.add("Route:         " + Global.RouteName);
        list.add("Dept DateTime: " + Global.VoucherDate + " " + Global.VoucherTime);
        list.add("VoucherNo:     " + Global.VoucherNo);
        list.add("UserName:      " + Global.User_Name);
        list.add("Vehicle:       " + Global.Vehicle_Name + Global.Vehicle_No);
        list.add("Version:       " + Global.appVersion);
        list.add("");
        list.add("+--------------------------------------+");
        list.add("|SEAT| SRC | DEST | FARE | DISC | AMNT |");
        for (CashDetail cashDetail : this.cashDetails) {
            if (cashDetail == null) {
                continue;
            }
            list.add("|----|-----|------|------|------|------|");
            list.add(String.format("|%-4s|%-5s|%-6s|%6s|%6s|%6s|",
                    cashDetail.getSeat_No(),
                    cashDetail.getSourceAbbr(),
                    cashDetail.getDestinationAbbr(),
                    String.format("%,.00f", cashDetail.getFare()),
                    String.format("%,.00f", cashDetail.getDiscount()),
                    String.format("%,.00f", cashDetail.getAmount())));
        }
        list.add("+--------------------------------------+");
        list.add("");
        list.add("----------------------------------------");
        Calendar c = Calendar.getInstance();
        list.add(String.format("Printed @: %1$tb %1$te, %1$tY, %1$tT", c));
        list.add("----------------------------------------");


        try {
            List<Integer> ids = new ArrayList<>(0);
            for (CashDetail detail : this.cashDetails) {
                if (detail == null || detail.getSeat() == null || detail.getSeat().getTicketing_Seat_ID() == 0) {
                    continue;
                }
                ids.add(detail.getSeat().getTicketing_Seat_ID());
            }
            if (!ids.isEmpty()) {
                this.ticketingSeatViewModel.setPrinted(ids);
            }


            ByteArrayOutputStream out = new ByteArrayOutputStream();

            out.write(new byte[]{ 0x1b, 0x61, 0x00 });
            for (String line : list) {
                out.write(("    " + line).getBytes());
                out.write(PrinterCommands.FEED_LINE);
//            this.sendData(("    " + line).getBytes());
//            this.printNewLine();
            }
            out.write(PrinterCommands.FEED_LINE);
            out.write(PrinterCommands.FEED_PAPER_AND_CUT);
            this.sendData(out.toByteArray());
            out.close();
        } catch (IOException e) {
            Log.e(getClass().getSimpleName(), "Error: " + e.getLocalizedMessage(), e);
            Toast.makeText(this.getContext(), "Error: " + e.getLocalizedMessage(), Toast.LENGTH_LONG);
        }
//        this.printNewLine();
//        this.sendData(PrinterCommands.FEED_PAPER_AND_CUT);
//        System.out.println(String.join("\r\n", list));
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(CashDetail item);
    }


    //print new line
    private void printNewLine() {
        sendData(PrinterCommands.FEED_LINE);
    }

    private void sendData(byte[] bytes) {
        try {
            if (MainActivity.mConnector != null && (MainActivity.mConnector.isConnected())) {
                MainActivity.mConnector.sendData(bytes);
            } else {
                Utile.showAlertDialog("Sorry! Printer is not conneted.", this.getContext()).show();
            }
        } catch (P25ConnectionException e) {
            e.printStackTrace();
        }
    }

}
