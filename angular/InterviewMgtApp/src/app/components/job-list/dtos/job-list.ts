export interface JobList {
  jobId: number;
  jobName: string;
  location: string;
  position: string;
  closingDate: Date;
  creationDate: Date;
  activeJob: boolean;
  assignTo : string[];
}
