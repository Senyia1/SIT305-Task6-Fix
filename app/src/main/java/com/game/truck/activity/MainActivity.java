package com.game.truck.activity;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;

import com.game.truck.App;
import com.game.truck.BaseBindingActivity;
import com.game.truck.adapter.MyAdapter;
import com.game.truck.bean.Truck;
import com.game.truck.DBHelper;
import com.game.truck.MenuPopupWindow;
import com.game.truck.bean.Order;
import com.game.truck.databinding.ActivityMainBinding;
import com.game.truck.databinding.ItemCarBinding;
import com.game.truck.databinding.ItemOrderBinding;
import com.game.truck.databinding.PopupwindowMenuBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {
    static List<Truck> trucks = new ArrayList<>();
    static {
        trucks.add(new Truck(App.randomGenerateReceiver(), App.randomInteger(), App.randomCarFace()));
        trucks.add(new Truck(App.randomGenerateReceiver(), App.randomInteger(), App.randomCarFace()));
        trucks.add(new Truck(App.randomGenerateReceiver(), App.randomInteger(), App.randomCarFace()));
        trucks.add(new Truck(App.randomGenerateReceiver(), App.randomInteger(), App.randomCarFace()));
        trucks.add(new Truck(App.randomGenerateReceiver(), App.randomInteger(), App.randomCarFace()));
        trucks.add(new Truck(App.randomGenerateReceiver(), App.randomInteger(), App.randomCarFace()));
    }
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
    private MyAdapter<ItemOrderBinding, Order> adapter = new MyAdapter<ItemOrderBinding, Order>() {
        @Override
        public ItemOrderBinding createHolder(ViewGroup parent) {
            return ItemOrderBinding.inflate(getLayoutInflater());
        }

        @Override
        public void bind(ItemOrderBinding itemOrderBinding, Order order, int position) {
            itemOrderBinding.tvReceiverName.setText("Receiver:" + order.receiverName);
            String format = dateFormat.format(Long.parseLong(order.timestamp));
            Log.d("ssss", "bind: " + format);
            itemOrderBinding.tvTimeStart.setText("Pick up time:" + format);
            itemOrderBinding.tvStartLoaction.setText("Pick up location:" + order.location);
            itemOrderBinding.face.setImageResource(order.face);
            itemOrderBinding.getRoot().setOnClickListener(view -> {
                startActivity(OrderInfoActivity.class, intent -> intent.putExtra("order_detail", order));
            });
            itemOrderBinding.ivShare.setOnClickListener(view -> {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,order.toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            });
        }
    };

    private MyAdapter<ItemCarBinding, Truck> carAdapter = new MyAdapter<ItemCarBinding, Truck>() {
        {
            getData().addAll(trucks);
        }

        @Override
        public ItemCarBinding createHolder(ViewGroup parent) {
            return ItemCarBinding.inflate(getLayoutInflater());
        }

        @Override
        public void bind(ItemCarBinding itemOrderBinding, Truck truck, int position) {
            itemOrderBinding.tvName.setText("Truck owner:" + truck.name);
            itemOrderBinding.tvPrice.setText("Price:" + truck.price + "$/h");
            itemOrderBinding.face.setImageResource(truck.face);
            itemOrderBinding.ivShare.setOnClickListener(view -> {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, truck.toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            });
        }
    };

    @Override
    protected void initListener() {

    }




    @Override
    protected void initData() {
        viewBinder.ivAddNew.setOnClickListener(view -> {
            startActivityForResult(CreateOrderOneActivity.class, null, 100);
        });

        viewBinder.ivMenu.setOnClickListener(view -> {
            PopupwindowMenuBinding binding = PopupwindowMenuBinding.inflate(getLayoutInflater());

            MenuPopupWindow popupWindow = new MenuPopupWindow(binding.getRoot());
            popupWindow.showAsDropDown(viewBinder.ivMenu);
            binding.getRoot().setOnClickListener(view1 -> {
                popupWindow.dismiss();
            });
            binding.tvMyOrder.setOnClickListener(view1 -> {
                viewBinder.recycler.setAdapter(adapter);
                popupWindow.dismiss();
            });
            binding.home.setOnClickListener(view1 -> {
                viewBinder.recycler.setAdapter(carAdapter);
                popupWindow.dismiss();
            });

        });
        adapter.getData().addAll(DBHelper.getHelper().queryMyOrder());
        viewBinder.recycler.setAdapter(carAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            adapter.getData().clear();
            adapter.getData().addAll(DBHelper.getHelper().queryMyOrder());
            adapter.notifyDataSetChanged();
        }
    }
}