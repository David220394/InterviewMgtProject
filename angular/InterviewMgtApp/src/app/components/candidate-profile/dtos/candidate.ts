import { Experience } from "./experience";
import { Skill } from "./skill";
import { Education } from "./education";

export interface Candidate {

  name : string,
  address : string,
  gender : string,
  dob : Date,
  applicationDate : Date,
  email : string,
  completeApplication : boolean,
  internalApplication : boolean,
  rehire : boolean,
  education : Education,
  availability : boolean,
  cover : string,
  score? : number,
  phone : number,
  status? : string,
  experience : Experience,
  skills : Skill[]
  jobName? : string;
}
