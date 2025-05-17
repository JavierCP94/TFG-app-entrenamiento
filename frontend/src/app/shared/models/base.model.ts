// Base interface for models with ID
export interface BaseModel {
  id?: string;
  createdAt?: Date;
  updatedAt?: Date;
}