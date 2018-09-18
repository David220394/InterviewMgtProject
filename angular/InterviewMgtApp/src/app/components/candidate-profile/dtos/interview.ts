import { Candidate } from "./candidate";

export interface Interview {
  candidateId : number;
  candidate? : Candidate;
  jobId : number;
  jobName?: string;
  interviewer : string;
  type : string;
  link : string;
}
