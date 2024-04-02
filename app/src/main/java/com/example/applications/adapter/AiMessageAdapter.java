package com.example.applications.adapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.airbnb.lottie.LottieDrawable.INFINITE;
import static com.example.applications.ai.AiMessage.SEND_BY_ME;
import static com.example.applications.ai.AiMessage.StreamMessage.string_end;
import static com.example.applications.ai.AiMessage.StreamMessage.string_start;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.applications.R;
import com.example.applications.ai.AiMessage;

import java.util.List;

public class AiMessageAdapter extends RecyclerView.Adapter<AiMessageAdapter.MyViewHolder> {
    private List<AiMessage> mMessageList;
    private Context mContext;

    public void setMMessageList(List<AiMessage> mMessageList) {
        this.mMessageList = mMessageList;
    }

    public AiMessageAdapter(Context context) {
        this.mContext = context;
    }

    public AiMessageAdapter(List<AiMessage> messageList) {
        this.mMessageList = messageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AiMessage message = mMessageList.get(position);
        if (message.getSentBy().equals(SEND_BY_ME)){
            holder.leftChatView.setVisibility(GONE);
            holder.leftChatImage.setVisibility(GONE);
            holder.left_loading.setVisibility(GONE);
            holder.rightChatView.setVisibility(VISIBLE);
            holder.rightChatImage.setVisibility(VISIBLE);
            Glide.with(mContext)
                    .load(R.drawable.me)
                    .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                    .into(holder.rightChatImage);
            holder.rightChatTextView.setText(message.getMessage());
        }else {
            holder.leftChatView.setVisibility(VISIBLE);
            holder.leftChatImage.setVisibility(VISIBLE);
            holder.left_loading.setVisibility(VISIBLE);
            Glide.with(mContext)
                    .load(R.drawable.e_icon)
                    .transform(new MultiTransformation<>(new CenterCrop(), new CircleCrop()))
                    .into(holder.leftChatImage);
            holder.rightChatView.setVisibility(GONE);
            holder.rightChatImage.setVisibility(GONE);
            holder.leftChatTextView.setText(message.getMessage());
            if (message.getStream() == string_start){
                holder.left_loading.setAnimation(R.raw.message_loading);
                holder.left_loading.setRepeatCount(INFINITE);
                holder.left_loading.playAnimation();
            }else if (message.getStream() == string_end){
                holder.left_loading.cancelAnimation();
                holder.left_loading.setVisibility(GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        if (mMessageList != null && mMessageList.size() > 0) {
            return mMessageList.size();
        } else {
            return 0;
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout leftChatView,rightChatView;
        private TextView leftChatTextView,rightChatTextView;
        private ImageView leftChatImage,rightChatImage;
        private LottieAnimationView left_loading;
        public MyViewHolder(@NonNull View v) {
            super(v);
            leftChatView = v.findViewById(R.id.left_chat_view);
            rightChatView = v.findViewById(R.id.right_chat_view);

            leftChatTextView = v.findViewById(R.id.left_chat_text_view);
            rightChatTextView = v.findViewById(R.id.right_chat_text_view);

            leftChatImage = v.findViewById(R.id.rob_icon);
            rightChatImage = v.findViewById(R.id.me_icon);

            left_loading = v.findViewById(R.id.left_loading);


            leftChatView.setOnLongClickListener(new OnLongClick());
            rightChatView.setOnLongClickListener(new OnLongClick());

            leftChatImage.setOnClickListener(new OnClick());
            rightChatImage.setOnClickListener(new OnClick());

        }

        private class OnClick implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                if (v == leftChatImage){
                    Toast.makeText(mContext,"leftChatImage onClick",Toast.LENGTH_SHORT).show();
                }else if (v == rightChatImage){
                    Toast.makeText(mContext,"rightChatImage onClick",Toast.LENGTH_SHORT).show();
                }
            }
        }

        private class OnLongClick implements View.OnLongClickListener {


            @Override
            public boolean onLongClick(View v) {
                if (v == leftChatView){
                    Toast.makeText(mContext,"leftChatView onLongClick",Toast.LENGTH_SHORT).show();
                }else if (v == rightChatView){
                    Toast.makeText(mContext,"rightChatView onLongClick",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        }

    }
}
