<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web servlets - A Java technology</title>
</head>
<body>
	<main>
		<h2>Welcome to Simple REST API</h2>

		<div style="margin:2rem;padding:2rem;border-radius:1em;box-shadow: rgba(100, 100, 100, 0.3) 0px 1px 4px 0px;">
    
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

		</div>


		<script defer>
			const studentForm = document.getElementById("studentForm")
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

				} catch(fetchError) {
					console.error("Error fetching the API: ", fetchError)
				}
			}

			studentForm.addEventListener("submit", async (e) => {
				e.preventDefault()
				await handleSubmit(e)
			})
		</script>
	</main>
</body>
</html>





