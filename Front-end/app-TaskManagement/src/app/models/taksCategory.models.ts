export interface TaskCategory {
    id: number;
    task_id: number;
    category_id: number;
    categories: {
        id: number;
        name: string;
    };
}