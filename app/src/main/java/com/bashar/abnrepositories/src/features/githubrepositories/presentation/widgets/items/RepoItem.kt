package com.bashar.abnrepositories.src.features.githubrepositories.presentation.widgets.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bashar.abnrepositories.src.features.githubrepositories.domain.model.Repo
import com.bashar.abnrepositories.src.features.githubrepositories.presentation.widgets.RepoAvatar

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RepoItem(
    repo: Repo = Repo(
        id = 111,
        name = "Repo Name",
        fullName = "Repo Full Name",
        ownerAvatar = "https://avatars.githubusercontent.com/u/1?v=4",
        ownerName = "ALi",
        isPrivate = false,
        description = "Repo Description",
        visibility = "Public",
        htmlUrl = "https://github.com/ALi/Repo Name",
    ), onClick: () -> Unit = {}
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        shadowElevation = 1.dp,
        tonalElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RepoAvatar(repo.ownerAvatar)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = repo.name, style = MaterialTheme.typography.titleMedium, maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = repo.fullName,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = if (repo.isPrivate) "Private" else "Public",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
