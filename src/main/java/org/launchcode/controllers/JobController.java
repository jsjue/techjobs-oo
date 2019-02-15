package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.launchcode.models.forms.SearchForm;


import javax.validation.Valid;
import java.security.AlgorithmConstraints;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view



        Job newJob = jobData.findById(id);
        model.addAttribute("job",newJob);



        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String processAddForm(Model model) {
        model.addAttribute(new JobForm());
        model.addAttribute("title", "Add job");
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(Model model, @Valid JobForm jobForm, Errors errors) {

        /**Job newJob = new Job(jobForm.getName(),
         jobData.getEmployers().findById(jobForm.getEmployerId()),
         jobData.getLocations().findById(jobForm.getLocation()),
         jobData.getPositionTypes().findById(jobForm.getPositionType()),
         jobData.getCoreCompetencies().findById(jobForm.getPositionType())
         );

         /**newJob.setName(jobForm.getName());
         newJob.setEmployer(jobData.getEmployers().findById(jobForm.getEmployers()));
         newJob.setLocation(jobData.getLocation().findById(jobForm.getLocation()));
         newJob.setPositionType(jobData.getPositionTypes().findById(jobForm.getPositionType()));
         newJob.setCoreCompetency(jobData.getCoreCompetencies().findById(jobForm.getPositionType()));**/


        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        if (errors.hasErrors()) {
            return "new-job";
        } else {
            String name = jobForm.getName();
            Employer employer = jobData.getEmployers().findById(jobForm.getEmployerId());

            Job newJob = new Job(name, employer, jobForm.getLocation(), jobForm.getPositionType(), jobForm.getCoreCompetency());

            /**Job newJob = new Job(jobForm.getName(), jobForm.getLocation(), jobForm.getPositionType(), jobForm.getCoreCompetency());**/

            jobData.add(newJob);


            return "redirect:?id=" + newJob.getId();

        }
    }
}