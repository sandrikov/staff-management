import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IProjectMember, NewProjectMember } from '../project-member.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IProjectMember for edit and NewProjectMemberFormGroupInput for create.
 */
type ProjectMemberFormGroupInput = IProjectMember | PartialWithRequiredKeyOf<NewProjectMember>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IProjectMember | NewProjectMember> = Omit<T, 'startDate' | 'endDate'> & {
  startDate?: string | null;
  endDate?: string | null;
};

type ProjectMemberFormRawValue = FormValueOf<IProjectMember>;

type NewProjectMemberFormRawValue = FormValueOf<NewProjectMember>;

type ProjectMemberFormDefaults = Pick<NewProjectMember, 'id' | 'startDate' | 'endDate'>;

type ProjectMemberFormGroupContent = {
  id: FormControl<ProjectMemberFormRawValue['id'] | NewProjectMember['id']>;
  startDate: FormControl<ProjectMemberFormRawValue['startDate']>;
  endDate: FormControl<ProjectMemberFormRawValue['endDate']>;
  project: FormControl<ProjectMemberFormRawValue['project']>;
  role: FormControl<ProjectMemberFormRawValue['role']>;
  employee: FormControl<ProjectMemberFormRawValue['employee']>;
};

export type ProjectMemberFormGroup = FormGroup<ProjectMemberFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ProjectMemberFormService {
  createProjectMemberFormGroup(projectMember: ProjectMemberFormGroupInput = { id: null }): ProjectMemberFormGroup {
    const projectMemberRawValue = this.convertProjectMemberToProjectMemberRawValue({
      ...this.getFormDefaults(),
      ...projectMember,
    });
    return new FormGroup<ProjectMemberFormGroupContent>({
      id: new FormControl(
        { value: projectMemberRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      startDate: new FormControl(projectMemberRawValue.startDate, {
        validators: [Validators.required],
      }),
      endDate: new FormControl(projectMemberRawValue.endDate),
      project: new FormControl(projectMemberRawValue.project, {
        validators: [Validators.required],
      }),
      role: new FormControl(projectMemberRawValue.role),
      employee: new FormControl(projectMemberRawValue.employee),
    });
  }

  getProjectMember(form: ProjectMemberFormGroup): IProjectMember | NewProjectMember {
    return this.convertProjectMemberRawValueToProjectMember(form.getRawValue() as ProjectMemberFormRawValue | NewProjectMemberFormRawValue);
  }

  resetForm(form: ProjectMemberFormGroup, projectMember: ProjectMemberFormGroupInput): void {
    const projectMemberRawValue = this.convertProjectMemberToProjectMemberRawValue({ ...this.getFormDefaults(), ...projectMember });
    form.reset(
      {
        ...projectMemberRawValue,
        id: { value: projectMemberRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ProjectMemberFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      startDate: currentTime,
      endDate: currentTime,
    };
  }

  private convertProjectMemberRawValueToProjectMember(
    rawProjectMember: ProjectMemberFormRawValue | NewProjectMemberFormRawValue,
  ): IProjectMember | NewProjectMember {
    return {
      ...rawProjectMember,
      startDate: dayjs(rawProjectMember.startDate, DATE_TIME_FORMAT),
      endDate: dayjs(rawProjectMember.endDate, DATE_TIME_FORMAT),
    };
  }

  private convertProjectMemberToProjectMemberRawValue(
    projectMember: IProjectMember | (Partial<NewProjectMember> & ProjectMemberFormDefaults),
  ): ProjectMemberFormRawValue | PartialWithRequiredKeyOf<NewProjectMemberFormRawValue> {
    return {
      ...projectMember,
      startDate: projectMember.startDate ? projectMember.startDate.format(DATE_TIME_FORMAT) : undefined,
      endDate: projectMember.endDate ? projectMember.endDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
