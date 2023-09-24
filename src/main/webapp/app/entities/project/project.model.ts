import { ProjectStatus } from 'app/entities/enumerations/project-status.model';

export interface IProject {
  id: number;
  projectName?: string | null;
  status?: keyof typeof ProjectStatus | null;
}

export type NewProject = Omit<IProject, 'id'> & { id: null };
