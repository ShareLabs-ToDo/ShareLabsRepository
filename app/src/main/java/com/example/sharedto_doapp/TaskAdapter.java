package com.example.sharedto_doapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharedto_doapp.models.Task;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    List<Task> tasks;
    Context context;

    public TaskAdapter(ArrayList<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View taskView = layoutInflater.inflate(R.layout.task_item, parent, false);
        return new ViewHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView taskTitleTV;
        public Button titleCheckbox;
        public TextView taskDeadlineTV;
        public ConstraintLayout tasksContainer;

        public ViewHolder(View taskView) {
            super(taskView);

            taskTitleTV = taskView.findViewById(R.id.task_title);
            titleCheckbox = taskView.findViewById(R.id.title_checkbox);
            taskDeadlineTV = taskView.findViewById(R.id.task_deadline);
            tasksContainer = taskView.findViewById(R.id.tasks_container);

        }

        public void bind(final Task task) {
            taskTitleTV.setText(task.getTitle());
            isDone(task.getIsDone());
            taskDeadlineTV.setText(task.getDeadline());

            tasksContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailedTaskActivity.class);
                    intent.putExtra("task", Parcels.wrap(task));
                    context.startActivity(intent);
                }
            });

            titleCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Boolean oldState = task.getIsDone();
                    Boolean newState = !oldState;

                    isDone(newState);
                    task.updateIsDone(task.getObjectId(), newState);
                }
            });
        }

        public void isDone(Boolean isDone) {
            if (isDone) {
                titleCheckbox.setBackgroundResource(R.drawable.ic_filled_check_box_24);
            } else {
                titleCheckbox.setBackgroundResource(R.drawable.ic_outline_check_box_24);
            }
        }
    }
}