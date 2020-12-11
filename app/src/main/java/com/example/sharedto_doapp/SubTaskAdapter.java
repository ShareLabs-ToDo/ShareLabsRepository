package com.example.sharedto_doapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharedto_doapp.models.Subtask;
import com.example.sharedto_doapp.models.Task;

import java.util.Arrays;
import java.util.List;

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.ViewHolder> {

    Task task;
    List<String> subtasks;
    Context context;
    public static final String TAG = "SubTaskAdapter";

    public SubTaskAdapter(Task task, List<String> subtasks, Context context) {
        this.task = task;
        this.subtasks = subtasks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View subtaskView = layoutInflater.inflate(R.layout.sub_task_item, parent, false);
        return new ViewHolder(subtaskView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubTaskAdapter.ViewHolder holder, int position) {
        String subtask = subtasks.get(position);
        holder.bind(subtask);
    }

    @Override
    public int getItemCount() {
        return subtasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView subTaskTV;
        public Button subTaskIsDone;

        public ViewHolder(View taskView) {
            super(taskView);

            subTaskTV = taskView.findViewById(R.id.task_title);
            subTaskIsDone = taskView.findViewById(R.id.task_checkbox);

        }

        public void bind(final String subtask) {
            String[] subtaskInfo = subtask.split(",");
            Log.i(TAG, Arrays.toString(subtaskInfo));
            String title = subtaskInfo[0];
            final boolean isDone = Boolean.parseBoolean(subtaskInfo[1]);
            subTaskTV.setText(title);
            isDone(isDone);

            subTaskIsDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean newState = !isDone;
                    isDone(newState);

                    int itemPosition = task.updateSubtasks(task.getObjectId(), subtask, subtasks);
                    notifyItemChanged(itemPosition);
                    notifyDataSetChanged();
                }
            });
        }

        public void isDone(boolean isDone) {
            if (isDone) {
                subTaskTV.setTextColor(ContextCompat.getColor(context, R.color.checkedTask));
                subTaskTV.setTypeface(subTaskTV.getTypeface(), Typeface.ITALIC);
                subTaskIsDone.setBackgroundResource(R.drawable.ic_filled_check_box_24);

            } else {
                subTaskTV.setTextColor(ContextCompat.getColor(context, R.color.uncheckedTask));
                subTaskTV.setTypeface(subTaskTV.getTypeface(), Typeface.BOLD);
                subTaskIsDone.setBackgroundResource(R.drawable.ic_outline_check_box_24);

            }
        }

    }
}