package com.asquare.bluetoothpairconnect;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder> {
    private Context context;
    private OnPairButtonClickListener onClickListener;
    private ArrayList<BluetoothDevice> bluetoothList;

    public DeviceListAdapter(Context context, ArrayList<BluetoothDevice> bluetoothList, OnPairButtonClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
        this.bluetoothList = bluetoothList;
    }


    @Override
    public DeviceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_iteam_devices, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeviceListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BluetoothDevice device = bluetoothList.get(position);
        holder.tvTitle.setText(device.getName());
        holder.tvAddress.setText(device.getAddress());
        holder.btPair.setText((device.getBondState() == BluetoothDevice.BOND_BONDED) ? "Unpair" : "Pair");
        holder.btPair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onPairButtonClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bluetoothList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvAddress;
        private Button btPair;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            btPair = itemView.findViewById(R.id.btn_pair);
        }

    }

    public interface OnPairButtonClickListener {
        void onPairButtonClick(int position);
    }
}
