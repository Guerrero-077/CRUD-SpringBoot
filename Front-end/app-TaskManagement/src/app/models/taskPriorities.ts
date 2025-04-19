import { Priorities } from "./priorities.models";
import { Tags } from "./Tags.models";

export interface TaskPriority {
    id: number;
    task_id: number;
    priority_id: number;
    tags: Tags[];
    priorities: Priorities[];
}
