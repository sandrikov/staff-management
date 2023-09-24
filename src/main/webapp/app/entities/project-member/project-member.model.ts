import dayjs from 'dayjs/esm';
import { IProject } from 'app/entities/project/project.model';
import { IRole } from 'app/entities/role/role.model';
import { IEmployee } from 'app/entities/employee/employee.model';

export interface IProjectMember {
  id: number;
  startDate?: dayjs.Dayjs | null;
  endDate?: dayjs.Dayjs | null;
  project?: Pick<IProject, 'id' | 'projectName'> | null;
  role?: Pick<IRole, 'id' | 'roleName'> | null;
  employee?: Pick<IEmployee, 'id' | 'lastName'> | null;
}

export type NewProjectMember = Omit<IProjectMember, 'id'> & { id: null };
