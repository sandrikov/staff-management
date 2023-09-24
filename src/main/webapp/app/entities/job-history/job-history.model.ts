import dayjs from 'dayjs/esm';
import { IJob } from 'app/entities/job/job.model';
import { IDepartment } from 'app/entities/department/department.model';
import { IEmployee } from 'app/entities/employee/employee.model';

export interface IJobHistory {
  id: number;
  startDate?: dayjs.Dayjs | null;
  endDate?: dayjs.Dayjs | null;
  job?: Pick<IJob, 'id' | 'jobTitle'> | null;
  department?: Pick<IDepartment, 'id' | 'departmentName'> | null;
  employee?: Pick<IEmployee, 'id' | 'lastName'> | null;
}

export type NewJobHistory = Omit<IJobHistory, 'id'> & { id: null };
