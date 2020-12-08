package com.example.sharedto_doapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharedto_doapp.models.Subtask;
import com.example.sharedto_doapp.models.Task;

import java.util.List;

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.ViewHolder> {

    List<Subtask> subtasks;
    Context context;

    public SubTaskAdapter(List<Subtask> subtasks, Context context) {
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
        Subtask subtask = subtasks.get(position);
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

        public void bind(Subtask subtask) {
            subTaskTV.setText(subtask.getTitle());
            isDone(subtask.getDone());
        }

        public void isDone(Boolean isDone) {
            if (isDone) {
                subTaskIsDone.setBackgroundResource(R.drawable.ic_filled_check_box_24);
            } else {
                subTaskIsDone.setBackgroundResource(R.drawable.ic_outline_check_box_24);
            }
        }

    }
}