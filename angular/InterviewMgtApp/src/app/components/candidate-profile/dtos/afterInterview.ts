import { InterviewFeedback } from "./interviewFeedback";

export interface AfterInterview{
  link : string;
  type : string;
  score : number;
  maxScore : number;
  feedback : string;
  interviewer? : string;
  endDate? : Date;
  questions? : InterviewFeedback[];
}
