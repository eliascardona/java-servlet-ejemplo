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

		<form id="studentForm">
			<label for="name">Name:</label>
			<input type="text" id="name" name="name">
			<br>

			<label for="grade">Grade:</label>
			<input type="number" id="grade" name="grade">
			<br>

			<label for="gradeId">Grade ID:</label>
			<input type="text" id="gradeId" name="gradeId">
			<br>

			<button type="submit">Submit</button>
		</form>

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
					name: e.target.name.value,
					grade: parseInt(e.target.grade.value),
					gradeId: e.target.gradeId.value
				}
				let fetchOptions = {
					...baseOptions,
					body: JSON.stringify(formData)
				}

				try {
					const r = await fetch("/EliasJSONOne/postOneStudent", fetchOptions)
					const j = await r.json()
					console.log(j)

			//		const load = j.load

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





