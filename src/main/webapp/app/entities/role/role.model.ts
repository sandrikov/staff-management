export interface IRole {
  id: number;
  roleName?: string | null;
}

export type NewRole = Omit<IRole, 'id'> & { id: null };
