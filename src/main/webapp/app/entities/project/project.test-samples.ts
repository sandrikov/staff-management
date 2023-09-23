import { IProject, NewProject } from './project.model';

export const sampleWithRequiredData: IProject = {
  id: 22281,
  projectName: 'strand ick uh-huh',
  status: 'CLOSED',
};

export const sampleWithPartialData: IProject = {
  id: 4197,
  projectName: 'braces amortise loftily',
  status: 'CLOSED',
};

export const sampleWithFullData: IProject = {
  id: 22142,
  projectName: 'round',
  status: 'CLOSED',
};

export const sampleWithNewData: NewProject = {
  projectName: 'throttle',
  status: 'CLOSED',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
