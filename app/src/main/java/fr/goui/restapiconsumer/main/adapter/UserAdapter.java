package fr.goui.restapiconsumer.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.goui.restapiconsumer.R;
import fr.goui.restapiconsumer.model.User;

/**
 *
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private List<User> mListOfUsers;

    public UserAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setListOfUsers(List<User> listOfUsers) {
        mListOfUsers = listOfUsers;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(mLayoutInflater.inflate(R.layout.layout_user, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.position = position;
        User user = mListOfUsers.get(position);
        if (user != null) {
            holder.firstnameTextView.setText(user.getFirstname());
            holder.lastnameTextView.setText(user.getLastname());
            holder.emailTextView.setText(user.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return mListOfUsers.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_firstname_textview)
        TextView firstnameTextView;

        @BindView(R.id.user_lastname_textview)
        TextView lastnameTextView;

        @BindView(R.id.user_email_textview)
        TextView emailTextView;

        int position;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
