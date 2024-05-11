<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Jakarta servlets - A Java technology</title>
	<style>
		.body {
			font-family: Arial, 'sans-serif';
		}
	</style>
</head>
<body class="body">
	<main>
		<h2>Test REST API based on JSON - POST METHOD</h2>

		<div style="width:55%;margin:2rem;padding:2rem;border-radius:1em;box-shadow: rgba(100, 100, 100, 0.3) 0px 1px 4px 0px;">
    
			<form id="studentForm">
				<label for="uname">Name:</label>
				<input type="text" id="uname" name="uname">
				<br>

				<label for="grade">Grade:</label>
				<input type="number" id="grade" name="grade">
				<br>

				<label for="gradeId">Grade ID:</label>
				<input type="text" id="gradeId" name="gradeId">
				<br>
				<button type="submit">Submit</button>
			</form>
			<br>

			<span style="display:block; margin-top:1rem;">
				<pre style="padding:2rem;">
					<code id="codeHere">
					</code>
				</pre>
			</span>

		</div>


		<script defer>
			const studentForm = document.getElementById("studentForm")
			const codeHere = document.getElementById("codeHere")

			let baseOptions = {
				method: "POST",
				headers: {
					"Content-Type": "application/json"
				},
				body: "json"
			}

			async function handleSubmit(evt) {
				let formData = {
					uname: evt.target.uname.value,
					grade: parseInt(evt.target.grade.value),
					gradeId: evt.target.gradeId.value
				}
				let fetchOptions = {
					...baseOptions,
					body: JSON.stringify(formData)
				}

				try {
					const r = await fetch("/EliasJSONOne/postOneStudent", fetchOptions)
					const j = await r.json()
					console.log(j)

					let aux = [...j]
					return aux

				} catch(fetchError) {
					console.error("Error fetching the API: ", fetchError)
				}
			}

			studentForm.addEventListener("submit", async (e) => {
				e.preventDefault()
				let respArr = await handleSubmit(e)

				let jsonString = JSON.stringify(respArr)

				if(jsonString.length > 0) {
					codeHere.innerText = jsonString
				} else {
					codeHere.innerText = "{ \"error\": \"error while formating response\" }"
				}

			})
		</script>
	</main>
</body>
</html>





