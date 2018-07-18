<!-- Error printout section -->
<div id="errors">
    <p>
        Please fix the following errors:
    </p>
    <div id="errorMessages">

    </div>
</div>

<!-- Form -->
<form method="post" action="submit.php" id="contactForm">
    <fieldset>
        <legend>Request for Information Form</legend>
        <table>
            <tr>
                <td>
                    <label for="firstname">First Name</label>
                </td>
                <td>
                    <input type="text" name="firstname" id="firstname" class="required hilightable" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="lastname">Last Name</label>
                </td>
                <td>
                    <input type="text" name="lastname" id="lastname" class="required hilightable" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email">Email</label>
                </td>
                <td>
                    <input type="email" name="email" id="email" placeholder="john.doe@gmail.com" class="required hilightable" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="phonenumber">Phone</label>
                </td>
                <td>
                    <input type="tel" name="phonenumber" id="phonenumber" placeholder="(123)456-7890" class="hilightable" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p>What infomration would you like to learn more about?</p>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="box">
                        <input id="general" value="general" type="checkbox" name="moreinfo"><label for="general">General addiction</label><br/>
                        <input id="internet" value="internet" type="checkbox" name="moreinfo"><label for="internet">Internet addiction</label><br/>
                        <input id="phone" value="phone" type="checkbox" name="moreinfo"><label for="phone">Phone addiction</label>
                    </div>
                </td>
                <td>
                    <div class="box">
                        <input id="help" value="help" type="checkbox" name="moreinfo"><label for="help">Addiction help and resources</label><br/>
                        <input id="groups" value="groups" type="checkbox" name="moreinfo"><label for="groups">Local groups and organizations</label><br/>
                        <input id="updates" value="updates" type="checkbox" name="moreinfo"><label for="updates">Weekly updates</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <label for="references">
                        How did you hear about us?
                    </label>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <select name="references"  id="references" class="hilightable">
                        <option selected> Choose One </option>
                        <option>Friend or family member</option>
                        <option>Web search</option>
                        <option>Group or organization</option>
                        <option>Posting or flier</option>
                        <option>Other</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="customcenter">
                    <div class="g-recaptcha centered" data-sitekey="6LePZVMUAAAAAEjWc_BX58W9CM9LGcE1eBXPMv_G"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Submit" id="register" name="register" class="button centered">
                </td>
                <td>
                    <input type="reset" value="Clear Form" id="clearform" name="clearform" class="button centered">
                </td>
            </tr>
        </table>
    </fieldset>
</form>