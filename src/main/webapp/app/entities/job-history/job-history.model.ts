import dayjs from 'dayjs/esm';
import { IJob } from 'app/entities/job/job.model';
import { IDepartment } from 'app/entities/department/department.model';
import { IEmployee } from 'app/entities/employee/employee.model';

export interface IJobHistory {
  id: number;
  startDate?: dayjs.Dayjs | null;
  endDate?: dayjs.Dayjs | null;
  job?: Pick<IJob, 'id'> | null;
  department?: Pick<IDepartment, 'id'> | null;
  employee?: Pick<IEmployee, 'id'> | null;
}

export type NewJobHistory = Omit<IJobHistory, 'id'> & { id: null };