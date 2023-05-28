package com.game.truck.activity;

import com.game.truck.App;
import com.game.truck.BaseBindingActivity;
import com.game.truck.bean.Order;
import com.game.truck.databinding.ActivityOrderInfoBinding;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class OrderInfoActivity extends BaseBindingActivity<ActivityOrderInfoBinding> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a", Locale.ENGLISH);

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        Order detail = (Order) getIntent().getSerializableExtra("order_detail");
        viewBinder.tvSender.setText("Form sender " + App.user.username);
        viewBinder.tvStartTime.setText("Pick up time: " + dateFormat.format(Long.parseLong(detail.timestamp)));
        viewBinder.tvReceiverName.setText("To receiver: " + detail.receiverName);
        viewBinder.tvEndTime.setText("Drop off time: " + dateFormat.format(Long.parseLong(detail.timestamp) + 60 * 1000 * 60 * 2));
        viewBinder.tvWeight.setText("Weight\n" + detail.weight + "kg");
        viewBinder.tvWidth.setText("Width\n" + detail.width + "m");
        viewBinder.tvHeight.setText("Height\n" + detail.height + "m");
        viewBinder.tvLength.setText("Length\n" + detail.length + "m");
        viewBinder.tvQuantity.setText("Quantity\n26");
        viewBinder.tvType.setText("Type\n" + detail.type);
        viewBinder.face.setImageResource(detail.face);

    }
}