import { TaskPriority } from "./taskPriorities";

export interface taskCreacion {
    title: string;
    description: string;
    expiration_date: string;
    tasksTags: TaskPriority[];
}


export interface task {
    id: number;
    title: string;
    description: string;
    creation_date: string;
    expiration_date: string;
    tasksTags: TaskPriority[];
}
