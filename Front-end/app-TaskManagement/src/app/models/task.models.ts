import { Priorities, RequestRegisterPriorities } from './priorities.models';
import { RequestRegisterReminders } from './reminders.models';
import { RequestRegisterSubTask } from './subTasks.models';


export interface taskCreacion {
    name: string;
    description: string;
    active: boolean;
    expiration_date: string;
    subTasks: RequestRegisterSubTask[];
    reminders: RequestRegisterReminders[];
    priorities: RequestRegisterPriorities[];
}


export interface task {
    id: number;
    name: string;
    description: string;
    creation_date: string;
    expiration_date: string;
    Priorities: Priorities[];
    subtaskName: string[];

}
