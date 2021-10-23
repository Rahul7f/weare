package ar.codeslu.plax.holders;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.auth.FirebaseAuth;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import ar.codeslu.plax.R;
import ar.codeslu.plax.fragments.Chats;
import ar.codeslu.plax.global.AppBack;
import ar.codeslu.plax.global.Global;
import ar.codeslu.plax.models.DefaultDialog;

/**
 * Created by CodeSlu on 21/03/19.
 */


public class DialogHolder extends DialogsListAdapter.DialogViewHolder<DefaultDialog> {

    FirebaseAuth mAuth;
    RoundedImageView ava, avaSmall;
    ImageView mute, block;

    public DialogHolder(View itemView) {
        super(itemView);
    }


    @Override
    public void onBind(DefaultDialog dialog) {
        super.onBind(dialog);
        mAuth = FirebaseAuth.getInstance();
        ava = itemView.findViewById(R.id.dialogAvatarC);
        mute = itemView.findViewById(R.id.mute);
        block = itemView.findViewById(R.id.block);
        avaSmall = itemView.findViewById(R.id.dialogLastMessageUserAvatarC);

        try {


            if (Global.mutelist.contains(dialog.getId()))
                mute.setVisibility(View.VISIBLE);
            else
                mute.setVisibility(View.GONE);

            if (Global.blockList.contains(dialog.getId()))
                block.setVisibility(View.VISIBLE);
            else
                block.setVisibility(View.GONE);
        } catch (NullPointerException e) {

        }

        try {
            if (String.valueOf(dialog.getDialogPhoto()).equals("no")) {
                Picasso.get()
                        .load(R.drawable.profile)
                        .placeholder(R.drawable.placeholder_gray).error(R.drawable.errorimg)
                        .into(ava);
            } else {
                Picasso.get()
                        .load(dialog.getDialogPhoto())
                        .placeholder(R.drawable.placeholder_gray).error(R.drawable.errorimg)
                        .into(ava);
            }
        } catch (NullPointerException e) {

        }


        try {
            if (dialog.getLastMessage().getId().equals(mAuth.getCurrentUser().getUid())) {
                if (Global.avaLocal.equals("no")) {
                    Picasso.get()
                            .load(R.drawable.profile)
                            .placeholder(R.drawable.placeholder_gray).error(R.drawable.errorimg)

                            .into(avaSmall);
                } else {
                    if(!Global.avaLocal.isEmpty()) {
                        Picasso.get()
                                .load(Global.avaLocal)
                                .placeholder(R.drawable.placeholder_gray).error(R.drawable.errorimg)

                                .into(avaSmall);
                    }
                }

            } else {

                if (String.valueOf(dialog.getDialogPhoto()).equals("no")) {
                    Picasso.get()
                            .load(R.drawable.profile)
                            .placeholder(R.drawable.placeholder_gray).error(R.drawable.errorimg)

                            .into(avaSmall);
                } else {
                    Picasso.get()
                            .load(dialog.getDialogPhoto())
                            .placeholder(R.drawable.placeholder_gray).error(R.drawable.errorimg)

                            .into(avaSmall);
                }

            }
        } catch (NullPointerException e) {

        }


        //dark mode init
        if (mAuth.getCurrentUser() != null) {
            if (!((AppBack) Global.mainActivity.getApplication()).shared().getBoolean("dark" + mAuth.getCurrentUser().getUid(), false)) {
                tvName.setTextColor(Global.conMain.getResources().getColor(R.color.black));
                tvDate.setTextColor(Global.conMain.getResources().getColor(R.color.black));
                tvLastMessage.setTextColor(Global.conMain.getResources().getColor(R.color.mid_grey));
            } else {
                tvName.setTextColor(Global.conMain.getResources().getColor(R.color.white));
                tvDate.setTextColor(Global.conMain.getResources().getColor(R.color.white));
                tvLastMessage.setTextColor(Global.conMain.getResources().getColor(R.color.light_mid_grey));
            }
        }
    }
}