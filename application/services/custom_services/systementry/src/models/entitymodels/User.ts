
export interface User 
{
   created_date: { type: Date },
   created_by: { type: String },
   last_modified_by: { type: String },
   last_modified_date: { type: Date },
   firstname: { type: String },
   lastname: { type: String },
   username: { type: String },
   email: { type: String },
   password: { type: String },
   signintype: { type: String },
   fbId: { type: String },
   githubId: { type: String },
   phonenumber: { type: String },
   avatar: { type: String },
   Idtoken: { type: String },
   loggedinDate: { type: Date },
   loggedoutDate: { type: Date },
   role: { type: String, ref: 'roles' },
   org: { type: String },
   org_country: { type: String },
   org_sub1: { type: String },
   org_sub2: { type: String },
   org_sub3: { type: String }
}



