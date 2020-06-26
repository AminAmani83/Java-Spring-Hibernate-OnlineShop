var rowBackup = {

	update : function(productId) {
		console.log("Update Button Clicked. Product ID: " + productId);
		
		// if another update is in progress, cancel it
		if ("trEelem" in rowBackup) {
			this.cancelEdit();
		}

		// Backup Current Row
		this.trEelem = document.getElementById("row" + productId);
		this.trEelemInnerHtmlBKP = this.trEelem.innerHTML;

		// Add fixed ID to the (hidden) form field
		$("td.fixed input", "#editform").attr("value", productId);

		// Add editable values to the form fields
		tdElemsOrig = $("td.editable", "#row" + productId);
		tdElemsForm = $("td.editable input", "#editform");
		for (let i = 0; i < tdElemsOrig.length; i++) {
			tdElemsForm[i].setAttribute("value", tdElemsOrig[i].innerText);
		}

		// Add fixed values to the table row
		tdElemsOrig = $("td.fixed", "#row" + productId);
		tdElemsTrgt = $("td.fixed span", "#editform");
		for (let i = 0; i < tdElemsOrig.length; i++) {
			tdElemsTrgt[i].innerHTML = tdElemsOrig[i].innerHTML;
		}

		// (A) save all the current values of fields that will be replaced by select boxes
		tdElemsOrig = this.trEelem.getElementsByClassName("selectbox");
		let arrayOfSelectedOptions = new Array();
		for (let i = 0 ; i < tdElemsOrig.length ; i++) {
			arrayOfSelectedOptions[i] = tdElemsOrig[i].innerText;	
		}
		
		// copy editform div over the editing row
		this.hiddenFormElem = document.getElementById("editform");
		this.trEelem.innerHTML = this.hiddenFormElem.innerHTML;

		// (B) set default selected value for select boxes (Must happen after copying the editform)
		// since moving the row resets the selected values.
		tdElemsForm = $("td.selectbox select", "#row" + productId);
		for (let i = 0; i < tdElemsOrig.length; i++) {
			tdElemsForm[i].querySelectorAll("option[value="+arrayOfSelectedOptions[i]+"]")[0].selected = true;
		}
		
		// backup the editform div and remove it to keep the ids unque
		this.hiddenFormElemInnerHtmlBkp = this.hiddenFormElem.innerHTML;
		this.hiddenFormElem.innerHTML = "";
	},

	cancelEdit : function() {
		console.log("Update Cancelled.");
		this.trEelem.innerHTML = this.trEelemInnerHtmlBKP;
		this.hiddenFormElem.innerHTML = this.hiddenFormElemInnerHtmlBkp;
	},
}


$("#show-add-item").click(function() {
	$("#add-item").show();
});


$("#hide-add-item").click(function() {
	$("#add-item").hide();
});


$(".delete a").click(function(event) {
	if (!confirm("Are you sure to delete?")) {
		event.preventDefault();
	}
});