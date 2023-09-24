import { IEmployee } from 'app/entities/employee/employee.model';

export interface IJob {
  id: number;
  jobTitle?: string | null;
  employee?: Pick<IEmployee, 'id' | 'lastName'> | null;
}

export type NewJob = Omit<IJob, 'id'> & { id: null };